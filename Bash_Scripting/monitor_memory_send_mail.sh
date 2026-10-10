#!/bin/bash
set -euo pipefail

IFS=' ' read -r total used free <<< $(free -m | grep Mem | tr -s ' ' | cut -d' ' -f2,3,4)
echo $total $used $free

used_percent=$((($used*100/$total)))

echo $used_percent

if [[ $used_percent -gt 80 ]]; then
    echo -e "Subject: Memory Alert\n\nSystem $(hostname) with IP $(hostname -i) triggered memory alert" | sendmail tchaitu.jkc@gmail.com
fi