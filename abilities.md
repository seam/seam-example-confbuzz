This document lists the abilities of the application confbuzz, both at a high level and
also more fine grained. The abilities are also backed by some examples to help
drive development. This application is being loosly modeled after lanyrd.com.

Application name: ConfBuzz
Tagline: the vibe of the conference circuit

## Visitor has ability to view ongoing conferences on home page

On the home page, any visitor should have the ability to view a list of ongoing conferences

  * The home page displays a list of each conference in progress (limit 5, ordered by start date desc; shuffle them in next iteration)
  * The list appears under the heading "Conferences Today"
  * Each conference is displayed in summary form #1
    * Line 1: name (displayed as link to detailed conference page)
    * Line 2: location (displayed as Country / City)
    * Line 3: date range (displayed as DD MMMM YYYY to DD MMMM YYYY) (exclude repeated elements, e.g. 20th to 23th June 2011)
    * Line 4: NN speaking, MM attending
  * When the user clicks the name, they are redirected to the detail page for that conference
  * Below the list, there is a link labeled "Browse all conferences" which redirects to a page of upcoming conferences

## Visitor has ability to view on now & upcoming conferences

On the upcoming conferences page, the user is presented with a list of all in progress & upcoming conferences

  * The main heading on the page is "Upcoming Conferences"
  * The list on the upcoming conferences page is divided by those in progress, then by month using horizontal rules
    * The horizontal rules have a label that is left justified under the line
    * The first horizontal rule is labeled "In Progress"
    * The remainder of the horizontal rules are labeled by the month and year
  * Each conference is displayed in summary form #2
    * Line 1: start date of month (in gutter), name (displayed as link to detailed conference page)
    * Line 2: location (displayed as Country / City)
    * Line 3: date range (displayed as DD MMMM YYYY to DD MMMM YYYY) (exclude repeated elements, e.g. 20th to 23th June 2011)
    * Line 4: tags (comma seperated or styled as individual boxes)
  * When the user clicks the name, they are redirected to the detail page for that conference

## Visitor has the ability to view conferences

The conference detail page displays all the information about the conference

  * The name is used as the page heading
  * The tagline (if available) is used a the page sub-heading
  * The remainder of information about the conference is shown in two columns as detail form #1
    * Line 1: date range (displayed as DD - DD MMMM YYYY), location (displayed as Country / City)
    * Line 2: URL, twitter hashtag
    * Line 3: list of tags (comma seperated or styled as individual boxes) (spanning both columns)
  * Below the conference details a list of speakers is displayed under the heading "Speakers"
  * The list of speakers are displayed in a grid
  * Each speaker is shown as their full name and twitter handle (avatar to be added in next iteration)
  * Below the list of speakers is a list of attendies, displayed under the heading "Attendees"
  * The list of attendies are displayed in a grid
  * Each attendee is shown as their full name and twitter handle (avatar to be added in next iteration)

## Visitor has the ability to view the conference session schedule

The conference session schedule page displays the list of sessions, in the order they occur

  * The heading of the page is "Schedule for %name%" where %name% is the name of the conference
  * The list of sessions is divided by date using horizontal rules
  * The date is left justified under the rule
  * The sessions are showed in detail session form #1
    * Line 1: Title
    * Line 2: Abstract
    * Line 3: Time range
    * Line 4: Speaker name

## User has ability to sign in / sign out

1. The user should be able to sign in with a username and password (sign in via twitter in next iteration)

  * Link is displayed in upper right hand corner of page
  * User supplies the correct cedentials
  * The site attempts to authenticate the credentials and redirects to the logged time view
  * User supplies incorrect credentials
  * The site attempts to authenticate the credentials and displays an error message stating the problem

2. The user will be automatically logged out after a time of inactivity

  * No actions happen from the user for a period of time
  * Upon the next interaction with the application the user is directed to sign in again due to inactivity

3. The user should be able to manually log out of the application

  * When a user manually logs out of the application they are redirected back to the main landing page of the site

4. If the user is authenticated, the sign in link is replaced with "you are signed in as %username%, do you want to sign out?"

## Authenticated user has ability to add / edit conferences

A conference contains a name, a tagline (optional), website (optional), a location, a starting date, an ending date, twitter hashtag

  * When a visitor is authenticated, they can enter a new conference
  * Upon selecting a conference, they can edit any of the above mentioned properties

At the time of adding a new conference or editing the attributes of an existing conference the dates are validated

  * The starting date must be equal to or greater than the current date
  * The ending date must be after the starting date

    The following data is input:
       * Start date: June 1st, 2011 (the current date is June 3rd, 2011)
       * End date: May 30th, 2011
       * Name: Acme Co.
    The system will alert the user that the start date occurs before the current date and the end date must be
    after the starting date.

    The following data is input:
       * Start date: 06/15/2011 (the current date is June 3rd, 2011)
       * End date: 2011/06/06
       * Name: Acme Co.
    The system will alert the user the ending date is before the starting date

    The following data is input:
       * Start date: 06-07-2011 (the current date is June 3rd, 2011)
       * End date: 07-01-2011
       * Name: Acme Co.
    The system accepts the date and creates a new project.

    The following data is edited
      * Start date: blah blah .34 #$%*
      * End date: 07-01-2011 (not changed)
      * Name: Acme Co. (not changed)
    The system alerts the user to invalid date entered for the start date

    The following data is input:
      * Start date:
      * End date:
      * Name:
    The sytem will alert the user to the missing name and start date.

## Authenticated user has the ability to identify themselves as a speaker or attendee

On the conference detail page, an authenticated user can click one of the following two buttons:

  * Add me as a speaker
  * Add me as an attendee

Clicking one of these buttons will mark the authenticated user as an speaker or attendee, respectively.

## User has ability to add their own sessions to a conference

A conference session consists of a title, abstract, date, start time, end time, speaker

The heading of the page is "Add a session to %name%", where %name% is the name of the conference

The form inputs are defined as follows

  * The title is required, max length 128
  * The abstract is optional, rows 10, columns 40
  * The date is required, should only allow dates of the conference using select menu
  * The start time and end time are required
  * The start time must be before the end time, and they cannot be equal

## Visitor has ability to export conference sessions into a report

The user should be able to export a view of sessions as (at least) a PDF report.

## Authenticated user has ability to view conferences on their schedule

The user should be able to view a list of conferences in which they are participating.

