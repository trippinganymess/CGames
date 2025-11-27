
#!/usr/bin/env python3
"""
Simple CSV -> JSON array converter.
Reads a CSV file with at least two columns (Number,Word) and writes a JSON array containing only the words
to the output path. Trims whitespace and skips empty entries.

Usage:
  python3 scripts/csv_to_json.py \
      --input /path/to/Adjectives.csv \
      --output /path/to/src/main/java/madLibs/Dictionary/adjs.json

If no input/output provided, defaults are used relative to the project root.
"""
import csv
import json
import argparse
import sys
from pathlib import Path

DEFAULT_INPUT = Path(__file__).resolve().parents[1] / 'Adjectives.csv'
DEFAULT_OUTPUT = Path(__file__).resolve().parents[1] / 'src' / 'main' / 'java' / 'madLibs' / 'Dictionary' / 'adjs.json'

parser = argparse.ArgumentParser(description='Convert CSV (Number,Word) to JSON array of words')
parser.add_argument('--input', '-i', type=Path, default=DEFAULT_INPUT, help='Path to input CSV')
parser.add_argument('--output', '-o', type=Path, default=DEFAULT_OUTPUT, help='Path to output JSON')
parser.add_argument('--encoding', '-e', default='utf-8', help='File encoding')
parser.add_argument('--sort', action='store_true', help='Sort the output words (default: preserve CSV order)')
args = parser.parse_args()

input_path: Path = args.input
output_path: Path = args.output

if not input_path.exists():
    print(f"Input CSV not found: {input_path}", file=sys.stderr)
    sys.exit(2)

# Ensure parent directory exists
output_path.parent.mkdir(parents=True, exist_ok=True)

words = []
with input_path.open('r', encoding=args.encoding, errors='replace') as fh:
    # Try to detect header and read by column name if possible
    reader = csv.reader(fh)
    try:
        header = next(reader)
    except StopIteration:
        print('Empty CSV file', file=sys.stderr)
        sys.exit(3)

    # Normalize header names
    header = [h.strip().lower() for h in header]
    # Find index of word column ("word" or second column)
    try:
        word_idx = header.index('word')
    except ValueError:
        # fallback to second column if available
        if len(header) >= 2:
            word_idx = 1
        else:
            print('Could not find a "Word" column in CSV header and no second column present', file=sys.stderr)
            sys.exit(4)

    # read remaining rows
    for row in reader:
        if len(row) <= word_idx:
            continue
        word = row[word_idx].strip()
        # skip empty and numeric-only
        if not word:
            continue
        # remove surrounding quotes if any
        if (word.startswith('"') and word.endswith('"')) or (word.startswith("'") and word.endswith("'")):
            word = word[1:-1].strip()
        # collapse multiple spaces
        word = ' '.join(word.split())
        if word:
            words.append(word)

if args.sort:
    words = sorted(words)

# write JSON array
with output_path.open('w', encoding='utf-8') as out:
    json.dump(words, out, ensure_ascii=False, indent=2)

print(f'Wrote {len(words)} words to {output_path}')
