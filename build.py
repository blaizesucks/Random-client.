#!/usr/bin/env python3
import sys, os

def read_text(p):
    with open(p, 'r', encoding='utf-8', errors='ignore') as f:
        return f.read()

def main():
    if len(sys.argv) < 3:
        print("Usage: python3 wrapper/build.py <path/to/original_offline.html> <out.html>")
        return 1
    inp = sys.argv[1]
    outp = sys.argv[2]
    tpl = os.path.join(os.path.dirname(__file__), "template_wrapper.html")
    src = read_text(inp)
    src_escaped = src.replace("</", "<\\/")
    tpl_text = read_text(tpl)
    out_text = tpl_text.replace("__SRCDOC__", src_escaped)
    os.makedirs(os.path.dirname(outp), exist_ok=True)
    with open(outp, "w", encoding="utf-8") as f:
        f.write(out_text)
    print("Wrote:", outp)
    return 0

if __name__ == "__main__":
    raise SystemExit(main())
