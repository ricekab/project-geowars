# ununderscore.py
#		Removes the underscores of all files in a given directory.
#		They cause the texture packer to ignore parts of the file name.
# Usage:
#		ununderscore.py planets/
# 	ununderscore.py sprite/foo/bar/

import os, sys

def ununderscore(path, files):
	for fileName in files:
		newName = fileName.replace("_", "")
		print(fileName)
		print(newName)
		os.rename(path + fileName, path + newName)
	
def main():
	argcount = len(sys.argv)
	if argcount == 2:
		path = sys.argv[1]
	else:
		print("Invalid argument count")
		sys.exit(0)
	print(path)
	files = os.listdir(path)
	ununderscore(path, files)

if __name__ == "__main__":
	main()