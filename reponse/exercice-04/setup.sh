#!/usr/bin/env bash
set -euo pipefail

NETWORK="ex04-net"
IMAGE="ubuntu-ping"
C1="ex04-a"
C2="ex04-b"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

docker network inspect "${NETWORK}" >/dev/null 2>&1 || docker network create "${NETWORK}"

docker build -t "${IMAGE}" "${SCRIPT_DIR}"

docker rm -f "${C1}" "${C2}" 2>/dev/null || true

docker run -d --name "${C1}" --network "${NETWORK}" "${IMAGE}"
docker run -d --name "${C2}" --network "${NETWORK}" "${IMAGE}"

echo "Ping ${C2} depuis ${C1} :"
docker exec "${C1}" ping -c 2 "${C2}"

echo "Ping ${C1} depuis ${C2} :"
docker exec "${C2}" ping -c 2 "${C1}"
