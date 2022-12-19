TESTFILE = Main.scala
OBJNAME = Main
RM = del
CD = cd

all:
	scalac -Wconf:any:silent Structures/*.scala Control/*.scala -explain

clean:
	$(RM) *.class
	$(RM) *.tasty
	$(RM) .\Structures\*.class
	$(RM) .\Structures\*.tasty
	$(RM) .\Control\*.class
	$(RM) .\Control\*.tasty

test:
	make
	scalac $(TESTFILE)
	scala $(OBJNAME)

run:
	scala $(OBJNAME)

testnoc:
	scalac $(TESTFILE)
	scala $(OBJNAME)
