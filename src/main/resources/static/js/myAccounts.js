document.addEventListener("DOMContentLoaded", function () {
    fetchUserAccounts();
});

function fetchUserAccounts() {

    const userId = 1;
    const apiUrl = `/accounts/user/${userId}`;

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            populateTable(data);
        })
        .catch(error => console.error("Error fetching accounts:", error));
}

function populateTable(accounts) {

    const tableBody = document.getElementById("accounts-body");

    accounts.forEach(account => {
        const row = tableBody.insertRow();
        row.insertCell(0).textContent = account.number;
        row.insertCell(1).textContent = account.name;
        row.insertCell(2).textContent = account.amount;
        row.insertCell(3).textContent = `${account.currency.name} (${account.currency.symbol})`;
        row.insertCell(4).textContent = account.openingDate;
    });
}