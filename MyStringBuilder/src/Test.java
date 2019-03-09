package martcd08_cs161_project1;
import java.util.ArrayList;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 1: MyStringBuilder
*
* Description: Test class to play around with the MyStringBuilder classes.
*/
public class Test {
    public static void main(String[] args){
        System.out.println(".::Testing constructors::.");
        /* Testing ArrayList<Character> Constructor*/
        // Declaring and instanciating an ArrayList<Character> object called alCharTest
        ArrayList<Character> alCharTest = new ArrayList<>();
        
        // Populating alCharTest with the characters a, b, c, d, e, f
        alCharTest.add('a');
        alCharTest.add('b');
        alCharTest.add('c');
        alCharTest.add('d');
        alCharTest.add('e');
        alCharTest.add('f');
        
        // Declaring and instanciating a MyStringBuilder object called arrayList with alCharTest
        MyStringBuilder arrayList = new MyStringBuilder(alCharTest);
        System.out.println("Calling toString() with arrayList:\n" + arrayList.toString());
        
        /* Testing String Constructor */
        // Declaring and instanciating a MyStringBuilder object called stringList with a test String
        MyStringBuilder stringList = new MyStringBuilder("This is just a String test.");
        System.out.println("Calling toString() with stringList:\n" + stringList.toString());
        
        /* Testing char[] Constructor */
        // Declaring and instanciating a char[] array to size 6 with z, y, x, w, x, u
        char[] arrayCharTest = {'z','y','x','w','v','u'};
        
        // Declaring and instanciating a MyStringBuilder object called charArray with arrayCharTest
        MyStringBuilder charArray = new MyStringBuilder(arrayCharTest);
        System.out.println("Calling toString() with charArray:\n" + charArray.toString());
        
        /* Testing MyStringBuilder Constructor */
        // Declaring and instanciating a MyStringBuilder object called msbTest with a test String
        MyStringBuilder msbTest = new MyStringBuilder("This will print if the copy worked correctly.");
        
        // Declaring and instanciating a MyStringBuilder object called msbTestCopy with msbTest
        MyStringBuilder msbTestCopy = new MyStringBuilder(msbTest);
        System.out.println("Calling toString() with msbTestCopy:\n" + msbTestCopy.toString());
        
        /* Testing the constructor that only initializes the size of the ArrayList */
        // Declaring and instanciating a MyStringBuilder object called sizeOnly with only an integer passed as a size
        MyStringBuilder sizeOnly =  new MyStringBuilder(10);
        System.out.println("Calling toString() with sizeOnly:\n" + sizeOnly.toString());
        
        /* Testing the default constructor */
        // Declaring and instanciating a MyStringBuilder object called defaultTest
        MyStringBuilder defaultTest = new MyStringBuilder();
        System.out.println("Calling toString() with defaultTest:\n" + defaultTest.toString());
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        /* Testing Methods */
        System.out.println(".::Testing methods::.");
        
        // Testing toArray() //
        // Declaring and instanciating a char[] array called testingToArray using the toArray() method
        char[] testingToArray = arrayList.toArray();
        System.out.print("Calling toArray() with arrayList:\n");
        // For loop to go through testingToArray and print each element
        for(int index = 0; index < testingToArray.length; index++){
            System.out.print(testingToArray[index]);
        }
        System.out.println("");
        
        // Testing convertToString() //
        System.out.println("Calling convertToString() with stringList:");
        System.out.println(stringList.convertToString());
        
        // Testing equals() //
        System.out.println("Calling equals() with stringList while passing charArray:");
        System.out.println(stringList.equals(charArray));
        System.out.println("Calling equals() with msbTest while passing msbTestCopy:");
        System.out.println(msbTest.equals(msbTestCopy));
        
        // Testing append() group //
        arrayList.append('w');
        System.out.println("Calling append('w') with arrayList:\n" + arrayList.toString());
        
        msbTest.append(msbTestCopy);
        System.out.println("Calling append(msbTestCopy) with msbTest:\n" + msbTest.toString());
        
        stringList.append("I am adding a string!");
        System.out.println("Calling append(\"I am adding a string!\") with stringList:\n" + stringList.toString());
        
        sizeOnly.append(charArray);
        System.out.println("Calling append(charArray) with sizeOnly:\n" + sizeOnly.toString());
        
        // Testing insert() group //
        defaultTest.insert(2, 'a');
        System.out.println("Calling insert(2, 'a') with defaultTest:\n" + defaultTest.toString());
        
        arrayList.insert(4,'$');
        System.out.println("Calling insert(4, '$') with arrayList:\n" + arrayList.toString());
        
        defaultTest.insert(3,arrayList);
        System.out.println("Calling insert(3, arrayList) with defaultTest:\n" + defaultTest.toString());
        
        arrayList.insert(0,charArray);
        System.out.println("Calling insert(0, charArray) with arrayList:\n" + arrayList.toString());
        
        arrayList.insert(-1,"This should be inserted at the end.");
        System.out.println("Calling insert(-1, \"This should be inserted at the end.\") with arrayList:\n" + arrayList.toString());
        
        defaultTest.insert(2,"This should start at index 2");
        System.out.println("Calling insert(2,\"This should start at index 2\") with defaultTest:\n" + defaultTest.toString());
        
        arrayList.insert(-5,charArray);
        System.out.println("Calling insert(-5,charArray) with arrayList:\n" + arrayList.toString());
        
        defaultTest.insert(5, charArray);
        System.out.println("Calling insert(5, charArray) with defaultTest:\n" + defaultTest.toString());
        
        sizeOnly.insert(-17, alCharTest);
        System.out.println("Calling insert(-17, alCharTest) with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.insert(5, alCharTest);
        System.out.println("Calling insert(5, alCharTest) with sizeOnly:\n" + sizeOnly.toString());
        
        // Testing delete() group //
        sizeOnly.delete(-1, 10);
        System.out.println("Calling delete(-1, 10) with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.delete(5,100);
        System.out.println("Calling delete(5,100) with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.deleteCharAt(5);
        System.out.println("Calling delete(5) with sizeOnly:\n" + sizeOnly.toString());
        
        arrayList.deleteAll(0, 54, 'e');
        System.out.println("Calling deleteAll(0, 54, 'e') with arrayList:\n" + arrayList.toString());
        
        // Testing replace() group //
        sizeOnly.replace(5, '*');
        System.out.println("Calling replace(5, '*') with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.replace(0, 5, stringList);
        System.out.println("Calling replace(0, 5, stringList) with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.replace(0,48,"***");
        System.out.println("Calling replace(0,48,\"***\") with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.replace(11, 17, charArray);
        System.out.println("Calling replace(11, 17, charArray) with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.replace(0, 17, alCharTest);
        System.out.println("Calling replace(0, 17, alCharTest) with sizeOnly:\n" + sizeOnly.toString());
        
        sizeOnly.setCharAt(0, '@');
        System.out.println("Calling setCharAt(0, '@') with sizeOnly:\n" + sizeOnly.toString());
        
        // Testing the String methods group //
        System.out.println("Calling charAt(10) with sizeOnly:\n" + sizeOnly.charAt(10));
        
        System.out.println("Calling indexOf('a') with sizeOnly:\n" + sizeOnly.indexOf('a'));
        
        System.out.println("Calling indexOf(\"This should\") with sizeOnly:\n" + sizeOnly.indexOf("This should"));
    }
}
/*
.::Testing constructors::.
Calling toString() with arrayList:
[a, b, c, d, e, f]
Calling toString() with stringList:
[T, h, i, s,  , i, s,  , j, u, s, t,  , a,  , S, t, r, i, n, g,  , t, e, s, t, .]
Calling toString() with charArray:
[z, y, x, w, v, u]
Calling toString() with msbTestCopy:
[T, h, i, s,  , w, i, l, l,  , p, r, i, n, t,  , i, f,  , t, h, e,  , c, o, p, y,  , w, o, r, k, e, d,  , c, o, r, r, e, c, t, l, y, .]
Calling toString() with sizeOnly:
[]
Calling toString() with defaultTest:
[]

.::Testing methods::.
Calling toArray() with arrayList:
abcdef
Calling convertToString() with stringList:
This is just a String test.
Calling equals() with stringList while passing charArray:
false
Calling equals() with msbTest while passing msbTestCopy:
true
Calling append('w') with arrayList:
[a, b, c, d, e, f, w]
Calling append(msbTestCopy) with msbTest:
[T, h, i, s,  , w, i, l, l,  , p, r, i, n, t,  , i, f,  , t, h, e,  , c, o, p, y,  , w, o, r, k, e, d,  , c, o, r, r, e, c, t, l, y, ., T, h, i, s,  , w, i, l, l,  , p, r, i, n, t,  , i, f,  , t, h, e,  , c, o, p, y,  , w, o, r, k, e, d,  , c, o, r, r, e, c, t, l, y, .]
Calling append("I am adding a string!") with stringList:
[T, h, i, s,  , i, s,  , j, u, s, t,  , a,  , S, t, r, i, n, g,  , t, e, s, t, ., I,  , a, m,  , a, d, d, i, n, g,  , a,  , s, t, r, i, n, g, !]
Calling append(charArray) with sizeOnly:
[z, y, x, w, v, u]
Calling insert(2, 'a') with defaultTest:
[a]
Calling insert(4, '$') with arrayList:
[a, b, c, d, $, e, f, w]
Calling insert(3, arrayList) with defaultTest:
[a, a, b, c, d, $, e, f, w]
Calling insert(0, charArray) with arrayList:
[z, y, x, w, v, u, a, b, c, d, $, e, f, w]
Calling insert(-1, "This should be inserted at the end.") with arrayList:
[z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, .]
Calling insert(2,"This should start at index 2") with defaultTest:
[a, a, T, h, i, s,  , s, h, o, u, l, d,  , s, t, a, r, t,  , a, t,  , i, n, d, e, x,  , 2, b, c, d, $, e, f, w]
Calling insert(-5,charArray) with arrayList:
[z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling insert(5, charArray) with defaultTest:
[a, a, T, h, i, z, y, x, w, v, u, s,  , s, h, o, u, l, d,  , s, t, a, r, t,  , a, t,  , i, n, d, e, x,  , 2, b, c, d, $, e, f, w]
Calling insert(-17, alCharTest) with sizeOnly:
[z, y, x, w, v, u, z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling insert(5, alCharTest) with sizeOnly:
[z, y, x, w, v, z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u, u, z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling delete(-1, 10) with sizeOnly:
[z, y, x, w, v, z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u, u, z, y, x, w, v, u, a, b, c, d, $, e, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , i, n, s, e, r, t, e, d,  , a, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling delete(5,100) with sizeOnly:
[z, y, x, w, v, t,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling delete(5) with sizeOnly:
[z, y, x, w, v,  , t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling deleteAll(0, 54, 'e') with arrayList:
[z, y, x, w, v, u, a, b, c, d, $, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b,  , i, n, s, r, t, d,  , a, t,  , t, h,  , n, d, ., z, y, x, w, v, u]
Calling replace(5, '*') with sizeOnly:
[z, y, x, w, v, *, t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling replace(0, 5, stringList) with sizeOnly:
[T, h, i, s,  , i, s,  , j, u, s, t,  , a,  , S, t, r, i, n, g,  , t, e, s, t, ., I,  , a, m,  , a, d, d, i, n, g,  , a,  , s, t, r, i, n, g, !, *, t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling replace(0,48,"***") with sizeOnly:
[*, *, *, *, t, h, e,  , e, n, d, ., z, y, x, w, v, u]
Calling replace(11, 17, charArray) with sizeOnly:
[*, *, *, *, t, h, e,  , e, n, d, z, y, x, w, v, u, u]
Calling replace(0, 17, alCharTest) with sizeOnly:
[z, y, x, w, v, u, a, b, c, d, $, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b,  , i, n, s, r, t, d,  , a, t,  , t, h,  , n, d, ., z, y, x, w, v, u, u]
Calling setCharAt(0, '@') with sizeOnly:
[@, y, x, w, v, u, a, b, c, d, $, f, w, T, h, i, s,  , s, h, o, u, l, d,  , b,  , i, n, s, r, t, d,  , a, t,  , t, h,  , n, d, ., z, y, x, w, v, u, u]
Calling charAt(10) with sizeOnly:
$
Calling indexOf('a') with sizeOnly:
6
Calling indexOf("This should") with sizeOnly:
13
*/