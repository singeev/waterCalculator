**Water calculator project**

There is a landscape with hills and pits which have similar square shape. Each
position in this landscape has a specified height. Max number of positions is 32000.
Height is between 0 and 32000.

For example: the first position has height 5, the second position has height 2. The
landscape can be presented as a collection of heights. {5,2,3,4,5,4,0,3,1}.

When rain happens, landscape is filled with water. Water is collected inside pits only
between hills. For example: collected 9 squares of water.

**How it works**

After application start you'll be asked to input desired landscape heights collection in a form of comma separated string.

Example input: 5,2,3,4,5,4,0,3,1

Then you hit "enter" and see the result: amount of water collected by your landscape.

To exit program just type "q" and hit Enter key.

If you type incorrect data the program will show you proper error massage.

**How to run program**

This is a maven based java 8 project.
First, you need to package project by command "mvn package". As a result you'll get "waterCalculator-1.0.jar" file in \targer folder  and cobertura code coverage report in \target\site\cobertura\index.html
To start program and begin to calculate water run command "java -cp target\waterCalculator-1.0.jar App".
