#!/usr/bin/env bash

gitlab-rails runner -e production "user = User.find_by(email: 'admin@example.com'); user.password_automatically_set = false; user.password = user.password_confirmation = 'mypassw0rd'; user.save!"