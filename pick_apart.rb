


whole_line = "    Depth  Strider  III"
chunk = whole_line.strip                     # "Depth  Strider  III"
pieces = chunk.split(WORD_SPACE).            # ["Depth", "Strider", "III"]

if %w[I II III IV V].include? pieces.last
  level = pieces.pop                         # "III"
  name = pieces.join(" ")                    # "Depth Strider"
else
  level = ""
  name = pieces.join(" ")
end