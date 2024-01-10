document.getElementById("newCardForm").addEventListener("submit",
    function (event) {

    event.preventDefault();

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
});