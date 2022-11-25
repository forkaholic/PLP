TESTFILE = temp.scala
OBJNAME = main

all:
	scalac Storage/Key.scala
	scalac Storage/Table.scala

clean:
	rm *.class

test:
	scalac $(TESTFILE)
	scala $(OBJNAME)