JC = javac
CP = .:
FLAGS = -g -cp $(CP) -d $(OUTPUT_DIR)
FIND = /bin/find
SOURCE_DIR =src
OUTPUT_DIR=jbin
all_javas = all.javas

.PHONY : all
all : $(all_javas)
	$(JC) $(FLAGS) @$<

.INTERMEDIATE : $(all_javas)
$(all_javas) :
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@

.PHONY : clean
clean:
	$(RM) *.class
