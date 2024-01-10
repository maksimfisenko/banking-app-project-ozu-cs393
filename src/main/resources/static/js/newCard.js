document.addEventListener("DOMContentLoaded", function (){

    fetchUserAccounts();

    const accountNumber = document.getElementById("card-account");
    const cardName = document.getElementById("card-name");

    const data = {
        accountNumber: accountNumber.value,
        cardName: cardName.value
    }

    fetch("/debitCards/open", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {console.log(data)})
        .catch(error => {console.log(error)});

    function fetchUserAccounts() {

        const userId = 2;
        const apiUrl = `accounts/user/${userId}`;

        const cardAccountSelect = document.getElementById("card-account");

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(account => {
                    const option = document.createElement("option");
                    option.value = account.id;
                    option.text = account.name;
                    cardAccountSelect.appendChild(option);
                });
            })
            .catch(error => console.error("Error fetching accounts:", error));
    }
});