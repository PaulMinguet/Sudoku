JVFLAGS  =	-g -implicit:none
JC   	 =	javac 
JV 		 =	java 

.SUFFIXES:	.java .class

.java.class:
	$(JC) $(JVFLAGS) $*.java

CLASSES =Solveur.java \
        Tableau.java \
        Creer.java \
        Grille.java \
        Grilleauto.java \
        Main.java 
 
TARGET = Main.java

all : $(*.java)
		$(JC) $(TARGET)

run 	:	
	$(JV) Main

clean 	:	
	rm -rf *.class