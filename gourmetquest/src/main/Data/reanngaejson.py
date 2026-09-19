import json


input_filename = "recipes.json"
output_filename = "formatted_data.json"


with open(input_filename, "r", encoding="utf-8") as file:
    single_line_data = json.load(file)


with open(output_filename, "w", encoding="utf-8") as file:
    json.dump(single_line_data, file, indent=4, ensure_ascii=False)

print(f"File successfully formatted and saved to {output_filename}")