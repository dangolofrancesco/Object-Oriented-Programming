<<<<<<< HEAD
Library
==============

Write a program to support book rentals and archiving in a public library.
Classes are located in the `it.polito.library` package; the main class is `LibraryManager`. The `TestExample` class in the `example` package shows usage examples for the main methods and examples of the requested checks. You are required to implement the requested checks only. Exceptions in the methods described below are of `LibException` type.

The [JDK documentation](https://oop.polito.it/api/) is located on the local server.

The Italian version of these requirements is available in [README_it.md](README_it.md).


R1: Readers and Books
------------------------

The library has several books in its archives, each having a given title. It is possible to archive several copies of the same book, and each one has a unique book ID.  Each unique book copy is linked to the list of its past rentals, and to its current rental status.

The `addBook()` method enables the library to add a new book to the list of books available for rental. It can be invoked multiple times, and in case the added book is a duplicate (i.e., it has the same title as a book already present in the archive), the method updates the number of copies available for the book. Each book copy has a unique book ID. Book IDs are numeric codes assigned incrementally starting from `1000`. The method returns the unique book ID assigned to the book as a `String` value. 

The `getTitles()` method returns the book titles available in the library, sorted alphabetically, each one linked to the number of copies available for that title. Returns the `SortedMap<String, Integer>` of the titles linked to the number of available copies.

The `getBooks()` method returns the set of unique IDs of the book copies available in the library as a `Set<String>`.

The `addReader()` method allows the library to add a new reader. It requires providing the reader's name and surname (as `String` values). Reader IDs are codes assigned incrementally starting from `1000` to each new reader. The library archive accepts readers with the same name and surname and simply assigns them different IDs. 

The `getReaderName()` method retrieves the name of a reader given their reader ID. It takes a reader ID as input and returns a `String` value representing their name and surname in the format `"Name Surname"`. If the ID does not exist in the archive, a `LibException` is thrown.


R2: Rentals Management
----------------------

Readers can rent one book at a time only. 

The `getAvailableBook()` method retrieves the bookID of an available book copy. The method takes the book title as input, and returns the book ID of the first copy available, in order of book ID, for a specific book as a `String` value. If all copies of the title are currently rented, it returns value `"Not available"`. If the title is not present in the library archive, a `LibException` is thrown.

The `startRental()` method allows the library to activate a rental of a book for a reader. It takes the unique book ID, the unique reader ID and the starting date as inputs. If the same reader rents the same unique book copy twice, the second rental overwrites the first one. If either the book or the reader are not registered in the library archive or either of them is currently involved in a rental, a `LibException` is thrown.

The `endRental()` method allows the library to end a rental of a specific book for a specific reader. Rentals are ended defining their ending date. This method updates the book's list of rentals, and the reader's rental status. It takes the unique book ID, the unique reader ID and the ending date as inputs. If either the book or the reader are not registered in the library archive, or the book is not currently rented, a `LibException` is thrown.

The `getRentals()` method retrieves the list of readers that rented a specific book. It takes a unique book ID an input, and returns the map (as a `SortedMap<String, String>`) of the readers' reader IDs and the starting and ending dates of each rental (as `String` values in the format `"DD-MM-YYYY DD-MM-YYYY"`). If a rental is ongoing, it reports only the starting date, and the word `"ONGOING"` in place of the ending date (in the format `"DD-MM-YYYY ONGOING"`).


R3: Book Donations
----------------

The `receiveDonation()` method collects books donated to the library. It takes in input a list of book titles (as `String` value in the format `"First title,Second title"`; titles can contain words separated only by `" "`). This method adds the received titles to the library archive. 


R4: Archive Management
----------------------

The method `getOngoingRentals()` gets all active rentals as a `Map<String, String>` linking reader IDs of renting readers and the book IDs of the books they are currently renting.

The method `removeBooks()` renews the book collection by removing from the archives all book copies, independently of the title, that were never rented.


R5: Stats
---------

The `findBookWorm()` method finds the reader with the highest total number of rentals and returns their unique reader ID as a `String` value.

The `rentalCounts()` method returns the number of total rentals (both ended and ongoing) for each title in the library as a map have the titles as keys and the number of rentals as values.
=======
# Exam-Library



## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://git-oop.polito.it/s295068/exam-library.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](https://git-oop.polito.it/s295068/exam-library/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thanks to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README

Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
>>>>>>> 9db357e751c5b1b7d1eeb3fbb297e5c7adbb5273
