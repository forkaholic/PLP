TESTFILE = temp.scala
OBJNAME = main
RM = del
CD = cd

all:
	scalac -Wconf:any:silent Structures/*.scala
	

clean:
	$(RM) *.class
	$(RM) *.tasty
	$(RM) .\Structures\*.class
	$(RM) .\Structures\*.tasty

test:
	make
	scalac $(TESTFILE)
	scala $(OBJNAME)

testnoc:
	scalac $(TESTFILE)
	scala $(OBJNAME)
