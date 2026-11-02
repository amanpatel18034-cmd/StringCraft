// StringCraft Frontend Logic

const API_BASE_URL = "http://localhost:8080/api/string";

// DOM Elements
const textInput = document.getElementById("text-input");
const charCount = document.getElementById("char-count");
const wordCount = document.getElementById("word-count");
const lineCount = document.getElementById("line-count");
const outputText = document.getElementById("output-text");
const outputContent = document.getElementById("output-content");
const outputPlaceholder = document.getElementById("output-placeholder");
const currentOpBadge = document.getElementById("current-op-badge");
const executionTime = document.getElementById("execution-time");
const btnCopy = document.getElementById("btn-copy");
const btnClear = document.getElementById("btn-clear");
const btnPaste = document.getElementById("btn-paste");
const btnSample = document.getElementById("btn-sample");
const historyList = document.getElementById("history-list");
const btnClearHistory = document.getElementById("btn-clear-history");
const themeToggle = document.getElementById("theme-toggle");
const toast = document.getElementById("toast");
const opCards = document.querySelectorAll(".op-card");

// State
let history = [];

// Sample Texts
const SAMPLE_TEXT = "StringCraft Toolkit 2026! Clean DSA Algorithms & Spring Boot 3.";

// Update Counters
function updateCounters() {
    const text = textInput.value;
    charCount.textContent = text.length;

    // Word count
    const words = text.trim() ? text.trim().split(/\s+/).length : 0;
    wordCount.textContent = words;

    // Line count
    const lines = text ? text.split("\n").length : 0;
    lineCount.textContent = lines;
}

textInput.addEventListener("input", updateCounters);

// Show Toast
function showToast(message) {
    toast.textContent = message;
    toast.classList.add("show");
    setTimeout(() => {
        toast.classList.remove("show");
    }, 2800);
}

// Fallback local DSA execution if backend server is not running
function localAlgorithmFallback(operation, text, mode) {
    switch (operation) {
        case "reverse":
            return text.split("").reverse().join("");
        case "count-vowels":
            return (text.match(/[aeiou]/gi) || []).length;
        case "count-consonants":
            return (text.match(/[bcdfghjklmnpqrstvwxyz]/gi) || []).length;
        case "count-digits":
            return (text.match(/[0-9]/g) || []).length;
        case "count-spaces":
            return (text.match(/\s/g) || []).length;
        case "remove-spaces":
            return text.replace(/\s+/g, "");
        case "convert-case":
            if (mode === "UPPER") return text.toUpperCase();
            if (mode === "LOWER") return text.toLowerCase();
            if (mode === "TOGGLE") {
                return text.split("").map(c => {
                    if (c === c.toUpperCase()) return c.toLowerCase();
                    return c.toUpperCase();
                }).join("");
            }
            return text;
        default:
            return text;
    }
}

// Execute Operation
async function executeOperation(operation) {
    const text = textInput.value;
    if (!text && operation !== "welcome") {
        showToast("Please enter some text in the editor first!");
        textInput.focus();
        return;
    }

    let mode = "UPPER";
    if (operation === "convert-case") {
        const checkedMode = document.querySelector('input[name="case-mode"]:checked');
        if (checkedMode) {
            mode = checkedMode.value;
        }
    }

    const startTime = performance.now();
    let result = null;
    let operationLabel = operation.replace("-", " ").toUpperCase();
    let isLiveBackend = true;

    try {
        const response = await fetch(`${API_BASE_URL}/${operation}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ text, mode })
        });

        if (!response.ok) {
            throw new Error(`Server returned HTTP ${response.status}`);
        }

        const data = await response.json();
        result = data.result;
    } catch (err) {
        // Graceful fallback to client-side equivalent if Spring Boot isn't active
        console.warn("Backend not reachable or error. Running local DSA fallback:", err);
        result = localAlgorithmFallback(operation, text, mode);
        isLiveBackend = false;
    }

    const duration = (performance.now() - startTime).toFixed(1);

    // Update Output
    outputPlaceholder.classList.add("hidden");
    outputContent.classList.remove("hidden");
    outputText.textContent = String(result);
    currentOpBadge.textContent = `${operationLabel} (${isLiveBackend ? "Backend" : "Local Simulator"})`;
    executionTime.innerHTML = `<i class="fa-regular fa-clock"></i> ${duration} ms`;

    // Add to History
    addHistoryItem(operationLabel, text, String(result));
    showToast(`Executed ${operationLabel} successfully!`);
}

// Add to History
function addHistoryItem(operation, input, result) {
    const item = {
        time: new Date().toLocaleTimeString(),
        operation,
        input: input.length > 30 ? input.substring(0, 30) + "..." : input,
        result: result.length > 30 ? result.substring(0, 30) + "..." : result
    };

    history.unshift(item);
    if (history.length > 10) history.pop();
    renderHistory();
}

function renderHistory() {
    if (history.length === 0) {
        historyList.innerHTML = `
            <tr class="empty-row">
                <td colspan="5">No operations executed yet. Start manipulating text above!</td>
            </tr>
        `;
        return;
    }

    historyList.innerHTML = history.map((item, idx) => `
        <tr>
            <td>${item.time}</td>
            <td><span class="badge-tag">${item.operation}</span></td>
            <td><code>${escapeHtml(item.input)}</code></td>
            <td><strong>${escapeHtml(item.result)}</strong></td>
            <td>
                <button class="btn btn-ghost btn-sm" onclick="restoreOutput('${idx}')">
                    <i class="fa-solid fa-arrow-up-right-from-square"></i> View
                </button>
            </td>
        </tr>
    `).join("");
}

window.restoreOutput = function(idx) {
    const item = history[idx];
    if (item) {
        outputPlaceholder.classList.add("hidden");
        outputContent.classList.remove("hidden");
        outputText.textContent = item.result;
        currentOpBadge.textContent = `Restored: ${item.operation}`;
        showToast("Restored from history");
    }
};

function escapeHtml(str) {
    return str
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}

// Operation Card Click Listeners
opCards.forEach(card => {
    const op = card.getAttribute("data-operation");
    card.addEventListener("click", (e) => {
        // Prevent click if clicking case radio
        if (e.target.tagName.toLowerCase() === "input") return;
        executeOperation(op);
    });
});

// Editor Actions
btnCopy.addEventListener("click", () => {
    const text = outputText.textContent;
    if (!text) {
        showToast("Nothing to copy!");
        return;
    }
    navigator.clipboard.writeText(text).then(() => {
        showToast("Copied to clipboard!");
    }).catch(() => {
        showToast("Failed to copy");
    });
});

btnClear.addEventListener("click", () => {
    textInput.value = "";
    updateCounters();
    outputPlaceholder.classList.remove("hidden");
    outputContent.classList.add("hidden");
    outputText.textContent = "";
    currentOpBadge.textContent = "Awaiting Input";
    showToast("Editor cleared");
});

btnPaste.addEventListener("click", async () => {
    try {
        const clipText = await navigator.clipboard.readText();
        textInput.value = clipText;
        updateCounters();
        showToast("Pasted from clipboard");
    } catch (e) {
        showToast("Clipboard access denied");
    }
});

btnSample.addEventListener("click", () => {
    textInput.value = SAMPLE_TEXT;
    updateCounters();
    showToast("Sample text loaded");
});

btnClearHistory.addEventListener("click", () => {
    history = [];
    renderHistory();
    showToast("History cleared");
});

// Theme Toggle
themeToggle.addEventListener("click", () => {
    const currentTheme = document.body.getAttribute("data-theme");
    const newTheme = currentTheme === "dark" ? "light" : "dark";
    document.body.setAttribute("data-theme", newTheme);
    localStorage.setItem("stringcraft-theme", newTheme);
});

// Check saved theme
const savedTheme = localStorage.getItem("stringcraft-theme");
if (savedTheme) {
    document.body.setAttribute("data-theme", savedTheme);
}

// Sidebar Navigation Smooth Scroll & Active Highlight
const navLinks = document.querySelectorAll(".nav-item");
navLinks.forEach(link => {
    link.addEventListener("click", (e) => {
        navLinks.forEach(l => l.classList.remove("active"));
        link.classList.add("active");
    });
});

// Initial Setup
updateCounters();

