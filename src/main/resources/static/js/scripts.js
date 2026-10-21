
window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});


//date picker
flatpickr("#futureDate", {
    dateFormat: "Y-m-d",
    altInput: true,
    altFormat: "F j, Y",
    allowInput: true,
    defaultDate: "today",
    minDate: "today",
    maxDate: new Date().getFullYear() + 10 + "-12-31",
});


//table
document.addEventListener('DOMContentLoaded', function () {
    $('#datatablesSimple').DataTable();
});



//for error modal
document.addEventListener("DOMContentLoaded", function () {
    /* Check if there is an error message from the server */
    var errorExists = /*[[${error != null}]]*/ false;
    if (errorExists) {
        var errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
        errorModal.show();
    }
});

//update
let selectedRow = null;

document.querySelectorAll('tbody tr').forEach(row => {
    row.addEventListener('click', () => {
        selectedRow = row;
    });
});

document.getElementById('updateBtn').addEventListener('click', (e) => {
    if (!selectedRow) {
        e.preventDefault();
        alert('Please select a row to update.');
        return;
    }

    // Read data-* attributes from selected row
    const flatId = selectedRow.getAttribute('data-id');
    const flatName = selectedRow.getAttribute('data-flat-name');
    const size = selectedRow.getAttribute('data-size');
    const bedrooms = selectedRow.getAttribute('data-noofbedroom');
    const dinning = selectedRow.getAttribute('data-noofdinningroom');
    const drawing = selectedRow.getAttribute('data-noofdrawingroom');
    const bathroom = selectedRow.getAttribute('data-noofbathroom');
    const kitchen = selectedRow.getAttribute('data-noofkitchen');
    const projectId = selectedRow.getAttribute('data-project-id');

    // Fill modal fields
    document.getElementById('upFlatId').value = flatId;
    document.getElementById('upFlatName').value = flatName;
    document.getElementById('upSize').value = size;
    document.getElementById('upNoOfBedRoom').value = bedrooms;
    document.getElementById('upNoOfDinningRoom').value = dinning;
    document.getElementById('upNoOfDrawingRoom').value = drawing;
    document.getElementById('upNoOfBathRoom').value = bathroom;
    document.getElementById('upNoOfKitchen').value = kitchen;
    document.getElementById('upProject').value = projectId;

    // Open modal
    const updateModal = new bootstrap.Modal(document.getElementById('updateFlatModal'));
    updateModal.show();
});


//filter expense table

function applyFilters() {
    const projectValue = document.getElementById("projectFilter").value.toLowerCase();
    const typeValue = document.getElementById("typeFilter").value.toLowerCase();
    const dateValue = document.getElementById("dateFilter").value;

    const rows = document.querySelectorAll("#datatablesSimple tbody tr");

    rows.forEach(row => {
        const project = row.cells[0].textContent.trim().toLowerCase();
        const type = row.cells[1].textContent.trim().toLowerCase();
        const date = row.cells[3].textContent.trim(); // assumed yyyy-MM-dd

        const matchProject = !projectValue || project === projectValue;
        const matchType = !typeValue || type === typeValue;
        const matchDate = !dateValue || date === dateValue;

        // Show only if all selected filters match
        row.style.display = (matchProject && matchType && matchDate) ? "" : "none";
    });

}


document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("projectFilter").addEventListener("change", applyFilters);
    document.getElementById("typeFilter").addEventListener("change", applyFilters);
    document.getElementById("dateFilter").addEventListener("change", applyFilters);
});


//filter flat table by project
function applyProjectFilter() {
    const projectValue = document.getElementById("projectFilter").value.toLowerCase();

    const rows = document.querySelectorAll("#datatablesSimple tbody tr");

    rows.forEach(row => {
        const project = row.cells[0].textContent.trim().toLowerCase();

        // Show row only if project matches or no project filter is selected
        row.style.display = (!projectValue || project === projectValue) ? "" : "none";
    });
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("projectFilter").addEventListener("change", applyProjectFilter);
});




function getAllMaterials() {
    const select = document.getElementById("materialSelect");
    return Array.from(select.options)
        .filter(opt => opt.value !== "")
        .map(opt => ({
            id: opt.value,
            name: opt.textContent.trim()
        }));
}

function showSuggestions(materials) {
    const suggestions = document.getElementById("materialSuggestions");
    suggestions.innerHTML = "";

    materials.forEach(opt => {
        const div = document.createElement("div");
        div.textContent = opt.name;
        div.classList.add("px-2", "py-1", "border-bottom");
        div.style.cursor = "pointer";
        div.onclick = function () {
            document.getElementById("materialInput").value = opt.name;
            document.getElementById("materialSelect").value = opt.id;
            suggestions.style.display = "none";
        };
        suggestions.appendChild(div);
    });

    suggestions.style.display = materials.length > 0 ? "block" : "none";
}


// 🛠 Hide the suggestion box if user clicks outside
document.addEventListener("click", function (e) {
    const input = document.getElementById("materialInput");
    const suggestions = document.getElementById("materialSuggestions");

    if (!input.contains(e.target) && !suggestions.contains(e.target)) {
        suggestions.style.display = "none";

        // If user typed something not from list, clear it (optional)
        const typed = input.value.trim().toLowerCase();
        const isValid = getAllMaterials().some(m => m.name.toLowerCase() === typed);
        if (!isValid && typed !== "") {
            input.value = "";
            document.getElementById("materialSelect").value = "";
            if (!valid) {
                input.value = "";
                select.value = "";
            }
        }
    }
});



function getAllFilterMaterials() {
    const select = document.getElementById("materialFilterSelect");
    let materials = Array.from(select.options)
        .filter(opt => opt.value !== "")  // exclude empty value option from here
        .map(opt => ({
            id: opt.value,
            name: opt.textContent.trim()
        }));

    // Always add "All Materials" as the first option for filtering
    materials.unshift({id: "", name: "All Materials"});

    return materials;
}


function showFilterSuggestions(materials) {
    const suggestions = document.getElementById("materialFilterSuggestions");
    suggestions.innerHTML = "";

    materials.forEach(opt => {
        const div = document.createElement("div");
        div.textContent = opt.name;
        div.classList.add("px-2", "py-1", "border-bottom");
        div.classList.add("dropdown-item"); // Bootstrap style
        div.style.cursor = "pointer";
        div.onclick = function () {
            document.getElementById("materialFilterInput").value = opt.name;
            document.getElementById("materialFilterSelect").value = opt.id;
            suggestions.style.display = "none";
            applyMaterialFilter();  // apply filter on selection
        };
        suggestions.appendChild(div);
    });

    suggestions.style.display = materials.length > 0 ? "block" : "none";
}



function applyMaterialFilter() {
    const materialSelect = document.getElementById("materialFilterSelect");
    const selectedMaterialId = materialSelect.value;
    const selectedMaterialName = materialSelect.options[materialSelect.selectedIndex]?.textContent.trim().toLowerCase();

    const rows = document.querySelectorAll("#datatablesSimple tbody tr");

    rows.forEach(row => {
        const materialInRow = row.cells[1].textContent.trim().toLowerCase(); // adjust column index if needed

        if (!selectedMaterialId || materialInRow === selectedMaterialName) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}


// ✅ Hide suggestion box on outside click + validate input
document.addEventListener("click", function (e) {
    const input = document.getElementById("materialFilterInput");
    const suggestions = document.getElementById("materialFilterSuggestions");

    if (!input.contains(e.target) && !suggestions.contains(e.target)) {
        suggestions.style.display = "none";

        const typed = input.value.trim().toLowerCase();
        const matched = getAllFilterMaterials().find(m => m.name.toLowerCase() === typed);

        if (matched) {
            document.getElementById("materialFilterSelect").value = matched.id;
        } else {
            input.value = "";
            document.getElementById("materialFilterSelect").value = "";
        }

        applyMaterialFilter();
    }
});


//
document.addEventListener('DOMContentLoaded', function () {
    const closeButtons = document.querySelectorAll('[data-bs-dismiss="modal"]');
    closeButtons.forEach(button => {
        button.addEventListener('click', function () {
            const modal = button.closest('.modal');
            if (modal) {
                modal.classList.remove('show', 'd-block');
                modal.style.display = 'none';

                const backdrop = document.querySelector('.modal-backdrop');
                if (backdrop) {
                    backdrop.remove();
                }
            }
        });
    });
});


function incomeExpensePieChart(income, expense) {

    income = (typeof income !== 'undefined') ? income : Number(document.getElementById('totalIncome').value || 0);
    expense = (typeof expense !== 'undefined') ? expense : Number(document.getElementById('totalExpense').value || 0);

    const data = (income === 0 && expense === 0)
        ? [['No Data', 1]]
        : [['Income', income], ['Expense', expense]];

    Highcharts.chart('myIncomePieChart', {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: 'Income & Expense'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}: {point.y}'
                }
            }
        },
        series: [{
            name: 'Amount',
            data: data
        }]
    });
}


//bar chart

function loadLast7DaysIncomeExpenseForProject() {
    const projectId = document.getElementById('projectSelectBarChart').value;
    if (!projectId) return;

    fetch(`/project-last7days-income-expense/${projectId}`)
        .then(response => response.json())
        .then(data => {
            renderLast7DaysBarChart(data.labels, data.income, data.expense);
        })
        .catch(error => console.error('Error loading 7 days project data:', error));
}

function renderLast7DaysBarChart(labels, incomeData, expenseData) {
    // labels = (typeof labels !== 'undefined') ? labels : JSON.parse(document.getElementById('totalLabels').value || "[]");
    // incomeData = (typeof incomeData !== 'undefined') ? incomeData : JSON.parse(document.getElementById('incomeData').value || "[]");
    // expenseData = (typeof expenseData !== 'undefined') ? expenseData : JSON.parse(document.getElementById('expenseData').value || "[]");

    const ctx = document.getElementById('last7DaysBarChart').getContext('2d');

    if (window.last7DaysChart) {
        window.last7DaysChart.destroy();
    }

    window.last7DaysChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Income',
                    data: incomeData,
                    backgroundColor: 'rgba(75, 192, 192, 0.6)'
                },
                {
                    label: 'Expense',
                    data: expenseData,
                    backgroundColor: 'rgba(255, 99, 132, 0.6)'
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Income & Expense (Last 7 Days)'
                }
            }
        }
    });
}

function renderLast7DaysBarChartFromHiddenInputs() {
    const labels = document.getElementById('labelsData').value.split(',');
    const income = document.getElementById('incomeData').value.split(',').map(Number);
    const expense = document.getElementById('expenseData').value.split(',').map(Number);

    renderLast7DaysBarChart(labels, income, expense);
}


    //if flat sale date select future date show error
    function validateFlatSaleDate() {
    const flatSaleInput = document.getElementById("flatSaleDate");
    const error = document.getElementById("dateError");

    const selectedDate = new Date(flatSaleInput.value);
    const today = new Date();

    // Normalize time
    selectedDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    let errorMessage = "";

    if (selectedDate > today) {
    errorMessage = "The selected sale date must be today or earlier.";
}

    if (errorMessage) {
    error.textContent = errorMessage;
    error.style.display = "block";
    flatSaleInput.classList.add("is-invalid");
    flatSaleInput.value = ""; // Clear invalid input
    return false;
} else {
    error.style.display = "none";
    error.textContent = "";
    flatSaleInput.classList.remove("is-invalid");
    return true;
}
}

    // Attach validation to form submit
    document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("addFlatForm").addEventListener("submit", function (e) {
        if (!validateFlatSaleDate()) {
            e.preventDefault();
        }
    });

    // Set max attribute dynamically (optional but useful for native browser enforcement)
    const flatSaleInput = document.getElementById("flatSaleDate");
    const today = new Date().toISOString().split("T")[0];
    flatSaleInput.setAttribute("max", today);
});


