document.addEventListener("DOMContentLoaded", function (){

    fetchUserAccounts();

    document.getElementById("new-card-form").addEventListener("submit", function (event) {

        const accountSelect = document.getElementById("card-account");
        const accountNumber = accountSelect.options[accountSelect.selectedIndex].value;
        const cardName = document.getElementById("card-name").value;

        if (cardName !== null && cardName.trim() !== '') {

            const data = {
                accountNumber: accountNumber,
                cardName: cardName
            };

            fetch("/debitCards/open", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                })
                .catch(error => {
                    console.log(error)
                });

            alert("The new card has been successfully created!");

        } else {
            alert("The name of the card cannot be empty!");
        }

    });

    function fetchUserAccounts() {

        const userId = 1;
        const apiUrl = `accounts/user/${userId}`;

        const cardAccountSelect = document.getElementById("card-account");

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(account => {
                    const option = document.createElement("option");
                    option.value = account.number;
                    option.text = account.name;
                    cardAccountSelect.appendChild(option);
                });
            })
            .catch(error => console.error("Error fetching accounts:", error));
    }
});