#!/bin/bash

set -o pipefail -e

usage="

$(basename "$0") [-h] [-e/d -p=\"password\" -f=\"path/to/file\"]

where:
    -h --help            show this help text
    -d --decrypt         decrypts the file
    -e --encrypt         encrypts the file
    -p --password        password used for
    -f --file            file to encrypt
    -o --output          (optional) if you want to write output to the file
"
# Encryption modes:
# 1 - encrypt (default)
# 0 - decrypt
mode=1

# Password used for encryption/decryption
password=

# Actual file for encryption/decryption
file=

# Optional output file
output=

while [[ $# -gt 0 ]]; do
  case "$1" in
    --help|-h)
      echo "$usage" >&2
      exit 0
      ;;
    --encrypt|-e)
      mode=1
      ;;
    --decrypt|-d)
      mode=0
      ;;
    --file=*|-f=*)
      file="${1#*=}"
      ;;
    --output=*|-o=*)
      output="--output ${1#*=}"
      ;;
    --password=*|-p=*)
      password="${1#*=}"
      ;;
    *)
      printf "Error: Invalid argument: $1"
      echo "$usage" >&2
      exit 1
  esac
 shift
done

## Verify we have all the arguments
if [[ -z "$password" ]]; then
    printf "Error: Missing argument: password"
    echo "$usage" >&2
    exit 1
elif [[ -z "$file" ]]; then
    printf "Error: Missing argument: file"
    echo "$usage" >&2
    exit 1
fi

if [[ ${mode} == 1 ]]; then
    gpg --quiet --batch --yes --symmetric --cipher-algo AES256 --passphrase=${password} ${file}
else
    gpg --quiet --batch --yes --decrypt ${output} --passphrase=${password} ${file}
fi