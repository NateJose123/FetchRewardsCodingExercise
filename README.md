# Fetch Rewards Coding Exercise
Android App built in java that fetches data from an api by fetch rewards and filters, sorts and displays the data in a list.

## User Stories

The following **required** functionality is completed:

- [x] User can view all items grouped by "listId".
- [x] Results are sorted first by "listID" and then by "name".
- [x] Results are filtered out where "name" is blank or null. 

The following **additional** features are implemented:

- [x] User is able to sort items in ascending as well as descending order.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/tH1RhOu.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


## Notes

Challenges: 
-Had a small string comparison issue in Java when trying to filter data. "==" wasn't working as it was referring to the object and not contents so .equals() was used to compare contents of "name" and make sure it wasn't blank or null.


## License

    Copyright [2021] [Nathan Jose]
