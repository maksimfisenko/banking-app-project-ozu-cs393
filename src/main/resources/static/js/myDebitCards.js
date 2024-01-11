document.addEventListener("DOMContentLoaded", function () {

    fetchUserAccounts();

    const accountSelect = document.getElementById("choose_account");

    accountSelect.addEventListener("change", function () {
        const selectedAccountNumber = accountSelect.options[accountSelect.selectedIndex].value;
        fetchAndDisplayDebitCards(selectedAccountNumber);
    });

    function fetchAndDisplayDebitCards(accountNumber) {

        const apiUrl = `accounts/debitCards/${accountNumber}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(debitCards => {
                populateTable(debitCards);
            })
            .catch(error => {
                const tableBody = document.getElementById("debit-cards-body");
                tableBody.innerHTML = "<tr><td colspan='3'>No debit cards available</td></tr>";
            });
    }

    function fetchUserAccounts() {

        const userId = 1;
        const apiUrl = `accounts/user/${userId}`;

        const cardAccountSelect = document.getElementById("choose_account");

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(account => {
                    const option = document.createElement("option");
                    option.value = account.number;
                    option.text = account.name;
                    cardAccountSelect.appendChild(option);
                });
                const selectedAccountNumber = cardAccountSelect.options[cardAccountSelect.selectedIndex].value;
                fetchAndDisplayDebitCards(selectedAccountNumber);
            })
            .catch(
                error => {
                    console.error("Error fetching accounts:", error);
                })
    }

    function populateTable(debitCards) {

        const tableBody = document.getElementById("debit-cards-body");
        tableBody.innerHTML = "";

        if (debitCards.length === 0) {
            const noCardsRow = tableBody.insertRow();
            const noCardsCell = noCardsRow.insertCell();
            noCardsCell.colSpan = 3;  // Span all columns
            noCardsCell.textContent = "No debit cards available.";
        } else {
            debitCards.forEach(debitCard => {
                const row = tableBody.insertRow();
                row.insertCell(0).textContent = debitCard.number;
                row.insertCell(1).textContent = debitCard.name;
                row.insertCell(2).textContent = debitCard.expirationDate;
            });
        }
    }
});