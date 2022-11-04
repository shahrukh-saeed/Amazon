Name: Shahrukh Saeed
ID: 501106701

> The program was compiling fine through my IDE (Netbeans) but through command prompt it gave me the error: 
	"Note: .\Shoes.java uses unchecked or unsafe operations.
	 Note: Recompile with -Xlint:unchecked for details."
  
  But once I added "SurpressWarnings("unchecked") to class Shoes it started working fine.

> When entering options for Book and Shoes, please keep in mind that it is case sensitive. For stock management in the shoe class I utilize .contains
  which is case-sensitive. Thus, the program will reject any input for options that does not match the case exactly.

> You cannot order Book or Shoe products through "ORDER". Also, you cannot order a non-Book/Shoe product through any other command as it will detect that the options do not match. You can only order through their respective commands.

> Other than that, everything seems to be fine, I followed along the video and all matched perfectly. Only things which are a bit different are the error
  messages as I did not make them exactly the same, but they do the job fine.

__A2 UPDATES__

> You must enter the correct option for each product inputted in addtocart (blank for no options), it is also case sensitive. It will reject incorrect options, similar to orderProduct/orderBooks/orderShoes explained above.
  (SHOES: "Black, 6" or "Black, 7" etc, BOOKS: Hardcover/Paperback/Ebook, OTHER: "" just press enter)

> If you add items to the cart then try to order them (using orderitems) and one of them has no stock, you will have to remove that item using "remcartitem" then try to order again.

> Also had to add @SuppressWarnings(unchecked) to ECommerceSystem.java to run in command prompt.

> I did not do the map related questions but my printbyname and printbyprice still work.