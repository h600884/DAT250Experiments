Når jeg prøver å lage en bruker gjennom "params"
{
"timestamp": "2024-09-03T20:03:02.447+00:00",
"status": 400,
"error": "Bad Request",
"path": "/users"
}

men fungerer gjennom JSON body data, dette er fordi urlen bruker to parametre mens metoden for å opprette en bruker har bare User user. 

Har ingen poll id, så måtte implementere det for å kunne finne en spesifikk poll. Kunne bare få en liste av alle pollsene som allerede eksisterte

Klarer å opprette en poll, men klarer ikke at en opprettet bruker kan "stemme" på en vote option

## Lab report: DAT250 Experiment 2


I began working on the lab before the Step-by-Step guide on GitHub was available, so I didn’t initially follow the approach outlined there. The main issue was that my initial implementation didn’t fully align with the requirements of the test we were supposed to run.

When I had done most of the classes I began with some testing using the HTTP client "Bruno". I have also done some JUnit testing with a test class. But due to some problems with my code the test does not pass. Since it has been some time since I have written such tests classes and due to limited time it was difficult getting the test to pass.  

The experiment is not completely finish, I have had some troubles getting the voting mechanism to work. But everything with the user works as expected, and I am able to create, delete and update a poll but not vote on it. Unfortunately I was not able to finish this due to running out of time with the assignment.   