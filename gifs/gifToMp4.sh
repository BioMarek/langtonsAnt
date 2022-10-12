#! /bin/bash
#
# This script finds all files with *.gif extension in current directory and converts them to files with *.mp4 extension.

# find finds all files with ".gif" extension, -type f means file not directory
# tr replaces ".gif" with "\n" and "/" with nothing
for i in $(find . -type f -name "*.gif" | tr ".gif" "\n" | tr "/" " ")
do
  echo $i
  # creates *.mp4
  ffmpeg -i $i.gif -vb 100M -vcodec libx264 -preset veryslow -vf "scale=trunc(iw/2)*2:trunc(ih/2)*2" $i.mp4
done



