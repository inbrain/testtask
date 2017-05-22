First of all: thanks for fairly interesting test assignment!
I had to remember how to work with parser combinators which I didn't touch for several 
years. Obviously this could have been done using ad-hoc parsing targeting this specific problem
but I prefer to approach the problem the same way I would do it under real work circumstances
.. and ofc there are other ways to parse it beyond scala parser combinators (which are 
not the most efficient but very convenient) and ad-hoc approach like Parboiled 
(I have only very general knowledge about) 

I didn't provide a main method, I suppose scala specs included should be representative enough 
and there should not be a problem to play with code in REPL.
An entry point to logic is Rewriter class which transforms String input into String input according
to the task description. Two examples of decode from task description are covered in RewriterSpec