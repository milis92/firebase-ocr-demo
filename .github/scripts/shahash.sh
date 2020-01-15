#!/bin/bash

set -o pipefail -e

echo $1 | xxd -r -p | openssl base64
