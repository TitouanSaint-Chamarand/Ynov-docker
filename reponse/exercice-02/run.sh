#!/usr/bin/env bash
set -euo pipefail

IMAGE="oats87/2048"
CONTAINER="ex02-2048"
PORT=8080

if docker ps --format '{{.Names}}' | grep -q "^${CONTAINER}$"; then
  echo "Conteneur ${CONTAINER} déjà en cours d'exécution."
  exit 0
fi

docker rm -f "${CONTAINER}" 2>/dev/null || true
docker pull "${IMAGE}"

pick_port() {
  local p=$1
  if ! lsof -iTCP:"${p}" -sTCP:LISTEN -P -n >/dev/null 2>&1; then
    echo "${p}"
    return
  fi
  if [ "${p}" -eq 8080 ]; then
    pick_port 8090
  else
    echo "Aucun port libre (8080/8090)." >&2
    exit 1
  fi
}

HOST_PORT="$(pick_port "${PORT}")"

docker run -d --name "${CONTAINER}" -p "${HOST_PORT}:80" "${IMAGE}"
echo "2048 disponible sur http://localhost:${HOST_PORT}"
