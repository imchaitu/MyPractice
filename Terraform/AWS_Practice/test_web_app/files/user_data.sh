#!/bin/bash

# Update packages and install NGINX
yum update -y
yum install nginx -y
systemctl start nginx
systemctl enable nginx

# Modify the default NGINX home page to show the instance hostname
echo "<h1>You are on instance: $(hostname -f)!</h1>" > /usr/share/nginx/html/index.html