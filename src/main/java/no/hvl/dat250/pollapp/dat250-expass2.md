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