#!/usr/bin/env bash
echo "Converting $1"

convert $1 -resize 2560x1707 -filter Cubic -quality 80 bg-xl.jpg
convert $1 -resize 1920x1280 -filter Cubic -quality 80 bg-l.jpg
convert $1 -resize 1200x800  -filter Cubic -quality 80 bg-m.jpg
convert $1 -resize 1000x667  -filter Cubic -quality 80 bg-s.jpg
convert $1 -resize 800x533   -filter Cubic -quality 80 bg-xs.jpg