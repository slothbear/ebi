


enchantment = "    Depth  Strider  III"
chunk = whole_line.strip                     # "Depth  Strider  III"
pieces = chunk.split(WORD_SPACE)             # ["Depth", "Strider", "III"]

if %w[I II III IV V].include? pieces.last
  level = pieces.pop                         # "III"
else
  level = 0
end

namePixels = textPixels(pieces)    # 1072 pixels in "Depth Strider"
