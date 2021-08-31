const projectList = document.getElementById('projectList');
const searchBar = document.getElementById('searchInput');
const allBtn = document.getElementById('all');

const allProjects = [];
fetch("https://innotal-competition.herokuapp.com/results/api-reg")
    .then(response => response.json())
    .then(data => {
        for (let d of data) {
            allProjects.push(d);
        }
    });

// Start of functions

allBtn.addEventListener('click', (e) => {
    projectList.innerHTML = ""
    displayProjects(allProjects);

})

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredProjects = allProjects.filter(p => {
        return p.name.toLowerCase().includes(searchingCharacters)
            || p.university.toLowerCase().includes(searchingCharacters);
    });
    projectList.innerHTML = ""
    displayProjects(filteredProjects);
})


function displayProjects(projects) {

    projectList.innerHTML = projects
        .map((p) => {
            return `    <tr>
                <td >${p.name}</td>
                <td >${p.university}</td>
                <td>${p.area}</td>
                <td>${p.panel}</td>
                  <td><p>${p.averageGrade}</p></td>
                   <td> <a href="/students/details/${p.id}">
                        <button type="button">Details</button></a>
                             </td>
            </tr> `
        })
        .join('');

}
