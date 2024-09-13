## Lab report: DAT250 experiment 3

I decided that for the frontend part of my project I would use Vue. This is even though I have more experience with React, but I wanted to try something new.
Because of this I had some problem setting up the frontend project because I used some time to familiarize myself with the structure of Vue.

After I had familiarized myself with Vue I had some problems using the correct body of for example the poll to actually be able to send in a POST request to create a new poll. 
Because of this I also had to do some changes to the backend to make it work.

One of the most challenging problems I came past in this project was to make a user able to change his vote after he´d already voted. This is something that works with Bruno when I try to manually make a API call.
The problem is that after the user has voted on a poll, and try to change his vote. The previous vote is not deleted and is not able to change. Since the user can not vote on more than one thing in a poll, the vote then never changes.

I have set up the voting site in my application that there is only a "Vote" button besides each option in the poll. This is instead of the "upvote" and "downvote" option that is stated in the assignment. 
This means that there is no way for the user the delete a vote if he regrets his vote. 
