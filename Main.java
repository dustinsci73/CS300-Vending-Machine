//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (descriptive title of the program making use of this file)
// Files:           (a list of all source files used by that program)
// Course:          (course number, term, and year)
//
// Author:          (Dustin Li)
// Email:           (dli284@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendingMachine vm = new VendingMachine(5);
		Item a  = new Item(20,"hello");
		Item b  = new Item(5,"hello");
		Item c  = new Item(2,"hello");
		Item d  = new Item(30,"hello");
		
		vm.addItem(a);
		vm.addItem(b);
		vm.addItem(c);
		vm.addItem(d);
		//System.out.println(a);
		System.out.println(vm.dispenseNextItem().getExpirationDay());
		System.out.println(vm.dispenseNextItem().getExpirationDay());
		System.out.println(vm.dispenseNextItem().getExpirationDay());
		System.out.println(vm.dispenseItemAtIndex(0).getExpirationDay());
		
		//vm.addItem(new Item(10,""));
		
		
	}

}
