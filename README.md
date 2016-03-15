# spellchecker
Create a spellchecker

<b>incorrect_spell_generator</b> has the program to generate incorrect words for a input word.

<b>spell_checker</b> has the program to suggest a word for given incorrect word. 

<b>How to run</b> 

cd spellchecker

mvn clean install

java -jar incorrect_spell_generator/target/incorrect_spell_generator-0.0.1.jar | java -jar spell_checker/target/spell_checker-0.0.1.jar 

&gt; &lt;Enter a word&gt;
