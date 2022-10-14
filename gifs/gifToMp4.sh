#! /bin/bash
#
# This script goes trough all folders and creates *mp4 from images in the folder

# -maxdepth 1 current directory won't be listed
for i in $(find . -maxdepth 1 -type d | tr "./" " ")
do
  cd $i
  rm $i.mp4  # removes previous video
  ffmpeg -framerate 30 -pattern_type glob -i '*.png' -vcodec libx264 $i.mp4
  cd ..
done

