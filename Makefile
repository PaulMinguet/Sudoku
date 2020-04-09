JAVAC = javac

JFLAGS = -d . \
		 -implicit:none \
         -Xlint:all \
         -encoding UTF-8

.SUFFIXES = .java .class

CLASSFILES = \
		./src/Main.java \
	    ./src/Solveur.java \
        ./src/Tableau.java \
        ./src/Creer.java \
        ./src/Grille.java \
        ./src/Grilleauto.java 

MAINCLASS = classfiles/main/Main

classfiles/main/Main.class : $(CLASSFILES) src/main/Main.java
	$(JAVAC) $(JFLAGS) src/main/Main.java

classfiles/affichage/Creer.class: src/affichage/Creer.java src/resolution/Tableau.java src/resolution/Solveur.java
	$(JAVAC) $(JFLAGS) src/affichage/Creer.java

classfiles/affichage/Grilleauto.class: src/affichage/Grilleauto.java src/resolution/Tableau.java src/resolution/Solveur.java
	$(JAVAC) $(JFLAGS) src/affichage/Grilleauto.java

classfiles/affichage/Grille.class: src/affichage/Grille.java src/resolution/Tableau.java src/resolution/Solveur.java
	$(JAVAC) $(JFLAGS) src/affichage/Grille.java

classfiles/resolution/Solveur.class: src/resolution/Solveur.java 
	$(JAVAC) $(JFLAGS) src/resolution/Solveur.java

classfiles/resolution/Tableau.class: src/Tableau/Resolution.java
	$(JAVAC) $(JFLAGS) src/resolution/Tableau.java


run: $(MAINCLASS).class
	java $(MAINCLASS)

clean:
	$(RM) classfiles/creation/*.class
	$(RM) classfiles/resolution/*.class
	$(RM) classfiles/affichage/*.class
	$(RM) classfiles/main/*.class