/*=============== SHOW MENU ===============*/
const showMenu = (toggleId, navId) =>{
   const toggle = document.getElementById(toggleId),
         nav = document.getElementById(navId)

   toggle.addEventListener('click', () =>{
       // Add show-menu class to nav menu
       nav.classList.toggle('show-menu')

       // Add show-icon to show and hide the menu icon
       toggle.classList.toggle('show-icon')
   })
}

showMenu('nav-toggle','nav-menu')

// Update data statistik kehadiran
document.getElementById('total-kehadiran').innerText = 1200;
document.getElementById('persentase-kehadiran').innerText = '80%';
document.getElementById('jumlah-ketidakhadiran').innerText = 300;

// Update data statistik mahasiswa
document.getElementById('jumlah-mahasiswa').innerText = 1500;
document.getElementById('mahasiswa-hadir').innerText = 1200;
document.getElementById('mahasiswa-tidak-hadir').innerText = 300;

// Update data statistik dosen
document.getElementById('jumlah-dosen').innerText = 100;
document.getElementById('dosen-hadir').innerText = 80;
document.getElementById('dosen-tidak-hadir').innerText = 20;

// Konfigurasi grafik kehadiran menggunakan Chart.js
var ctx = document.getElementById('attendanceChart').getContext('2d');
var attendanceChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Senin', 'Selasa', 'Rabu', 'Kamis', 'Jumat'],
        datasets: [{
            label: 'Jumlah Kehadiran',
            data: [200, 220, 250, 230, 300],
            backgroundColor: [
                'rgba(78, 115, 223, 0.6)',
                'rgba(78, 115, 223, 0.6)',
                'rgba(78, 115, 223, 0.6)',
                'rgba(78, 115, 223, 0.6)',
                'rgba(78, 115, 223, 0.6)'
            ],
            borderColor: [
                'rgba(78, 115, 223, 1)',
                'rgba(78, 115, 223, 1)',
                'rgba(78, 115, 223, 1)',
                'rgba(78, 115, 223, 1)',
                'rgba(78, 115, 223, 1)'
            ],
            borderWidth: 2
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});