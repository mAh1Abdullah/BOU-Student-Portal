#!/bin/bash
# BOU Portal Web Dev Server Supervisor
LOG_FILE="/var/log/node-server.log"
APPLET_DIR="/app/applet"

echo "[$(date)] Web server supervisor started" >> "$LOG_FILE"

while true; do
  # Check if server is answering on port 3000
  if ! curl -s -f http://localhost:3000/api/health > /dev/null 2>&1; then
    # Kill any stale node server process
    pkill -f "node.*server.js" 2>/dev/null || true
    sleep 0.5
    echo "[$(date)] Starting node server.js on port 3000..." >> "$LOG_FILE"
    (
      cd "$APPLET_DIR" && exec node server.js >> "$LOG_FILE" 2>&1
    ) &
    sleep 2
  fi
  sleep 2
done
