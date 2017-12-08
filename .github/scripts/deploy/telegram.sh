#!/usr/bin/env bash

#partially taken from https://github.com/Deletescape-Media/Lawnchair

curl -F chat_id='-1001391347756' -F document=@"app/build/outputs/apk/debug/app-debug.apk" https://api.telegram.org/bot$BOT_TOKEN/sendDocument
curl -F chat_id='-1001391347756' -F text="$(git log -1 $TRAVIS_COMMIT --pretty="Commit by %aN (_%aE_)%n%s")" -F parse_mode="markdown" https://api.telegram.org/bot$BOT_TOKEN/sendMessage

