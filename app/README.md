# 📊 Daily Expense Tracker (Android)

A simple and modern Daily Expense Tracker Android app built with Kotlin, Jetpack Compose, Room Database, and Material 3.  
It helps you record your daily expenses, categorize them, and view summaries with charts.

---

## ✨ Features

- ➕ Add, edit, and delete expenses
- 📅 Track expenses with date and description
- 🏷️ Categorize your spending (Food, Travel, Shopping, etc.)
- 📊 View expense summary with interactive charts
- 🎨 Modern Material 3 UI with light/dark theme
- 💾 Offline-first with Room Database

---

## 🛠️ Tech Stack

- [Kotlin](https://kotlinlang.org/) – Primary language
- [Jetpack Compose](https://developer.android.com/jetpack/compose) – UI toolkit
- [Material 3](https://m3.material.io/) – Modern design system
- [Room Database](https://developer.android.com/training/data-storage/room) – Local data persistence
- [Kotlin Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html) – Asynchronous programming
- [Coil](https://coil-kt.github.io/coil/compose/) – Image loading
- [Compose Charts](https://github.com/bytebeats/compose-charts) – Pie & Bar charts

---

---

## 🚀 Getting Started

### 1️⃣ Clone the repository
```bash
git clone https://github.com/yourusername/DailyExpenseTracker.git
cd DailyExpenseTracker

---

## ⚙️ Requirements

Minimum SDK: 24 (Android 7.0 Nougat)
Target SDK: 36
Compile SDK: 36
Gradle Plugin: 8.1.2 (or newer)

## 📂 Project Structure

                    app/
                     ├─ data/
                     │   ├─ dao/          # Room DAO interfaces
                     │   ├─ entity/       # Expense entity
                     │   └─ AppDatabase.kt
                     ├─ repository/
                     ├─ ui/
                     │   ├─ theme/        # Material 3 Theme setup
                     │   ├─ ExpenseScreen.kt
                     │   └─ PieChartSummaryScreen.kt
                     ├─ viewmodel/        # ViewModel + State management
                     └─ MainActivity.kt   # App entry point
                    