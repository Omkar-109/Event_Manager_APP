# Event Manager App Documentation
## Overview
The Event Manager App is a mobile application built using Java for the Android platform. It is designed to assist users in planning and organizing events effectively. Key features include managing events, guests, vendors, and event budgets, with all data securely stored using SQLite, a lightweight local database. The app offers an intuitive interface, allowing users to easily add, edit, and delete event-related details.
________________________________________
## Key Features
### 1. Event Creation and Management
*	Create Events: Users can add new events by specifying details such as the event name, date, venue, and description.
*	Event List: A comprehensive list of all events is shown on the main screen, displaying key information (name, date, and location).
*	Event Details: Users can select an event to view its complete information, including the list of guests, vendors, and budget breakdown.
### 2. Guest Management
*	Add Guests: Users can assign guests to specific events, entering details like guest name, contact information, and their RSVP status.
*	Track RSVPs: Users can easily see the RSVP status of all guests, categorized into attending, declined, or pending.
### 3. Vendor Management
*	Add Vendors: Users can add vendors to the event, such as caterers, decorators, or entertainment services, along with contact information and notes.
*	View Vendor List: All vendors associated with a particular event are displayed, allowing users to manage service providers efficiently.
### 4. Budget and Payment Management
*	Set Budget: Users can assign a budget for each event and track the expenditures made.
*	Add Payments: New payments can be recorded with relevant details such as payment name, amount, and date. Each payment is linked to its event.
*	Track Expenditures: The app automatically calculates the remaining balance based on the payments entered, giving users a clear view of their spending.
### 5. Local Data Storage with SQLite
*	The app uses an SQLite database to store data, which ensures that event information, guest lists, vendor details, and budget information are saved on the user’s device and persist between sessions.
### 6. User Interface and Navigation
*	Main Screen: The home screen provides four key options (Events, Guests, Vendors, Budget) for easy navigation between the app’s main functionalities.
*	Floating Action Button (FAB): In each section of the app, users can add new entries (events, guests, vendors, or payments) using the floating action button.
*	List Views: Events, guests, vendors, and payments are displayed using a clean and responsive list format for easy access.
________________________________________
## Application Structure
### 1. Activities
*	MainActivity: The app’s entry point, providing navigation to the four main sections—Events, Guests, Vendors, and Budgets.
*	EventsActivity: Displays all events and allows users to add, edit, or delete event details.
*	GuestsActivity: Manages guest information, providing options to add, edit, or remove guests from specific events.
*	VendorsActivity: Handles vendor management for events, allowing users to view, add, and edit vendor information.
*	BudgetActivity: Displays the budget overview and payments for each event, with the ability to add new payments or view the remaining budget.
*	EventDetailActivity: Provides a detailed view of a selected event, including its associated guests, vendors, and financial breakdown.
*	PaymentDialog: A modal window that pops up to add payment details.
### 2. Database
*	The app’s backend is powered by SQLite, which stores all data locally. This ensures users’ event data is stored securely on their devices, without needing an internet connection.
*	Database Schema:
*	Events Table: Stores event details like event name, date, and venue.
*	Guests Table: Holds guest information, linked to their respective events.
*	Vendors Table: Contains vendor details associated with specific events.
*	Payments Table: Stores payment details, allowing for budget tracking.
________________________________________
## Technology Stack
*	Java: The programming language used to develop the app.
*	Android SDK: Provides the libraries and tools for building the Android app.
*	SQLite: A database engine used for storing and retrieving event, guest, vendor, and payment data.
*	Figma: Figma is used to create prototype of app to test design and get an idea of activity flow.
________________________________________
## Development Timeline
### Phase 1: 
*	Define project scope and objective.
*	Select technologies.
### Phase 2:
*	Set up the development environment and create the basic app structure.
*	Implement core features such as Event creation and basic app structure.
*	Integrate additional features such as budget tracking and Vendor management.
### Phase 3:
*	Conduct unit and integration testing.
*	Working on UI/UX improvements.
*	Gather feeback.
*	Refine the app based on feedback.


________________________________________
## Future Plans
*	Cloud Sync: Future iterations of the app could include cloud synchronization to allow users to back up their data online and sync across multiple devices.
*	Reminders: Adding notification reminders for event deadlines or pending tasks to enhance event planning.
*	Export Data: An option to export event and budget details as PDF or CSV files for sharing with others or for personal records.
