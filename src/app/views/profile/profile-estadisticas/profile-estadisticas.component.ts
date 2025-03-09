import { AfterViewInit, Component } from '@angular/core';
import { Chart, registerables} from 'chart.js';
import { ESTADISTICAS } from '../../../datos_estaticos/user_estadisticas';

Chart.register(...registerables);

@Component({
  selector: 'app-profile-estadisticas',
  imports: [],
  standalone: true,
  templateUrl: './profile-estadisticas.component.html',
  styleUrl: './profile-estadisticas.component.css'
})
export class ProfileEstadisticasComponent implements AfterViewInit{
// Datos de ejemplo (estos serán sustituidos por los datos reales de la base de datos)
participatedBids = ESTADISTICAS.participatedBids;
wonBids = ESTADISTICAS.wonBids;
createdBids = ESTADISTICAS.createdBids;
participatedDraws = ESTADISTICAS.participatedDraws;
wonDraws = ESTADISTICAS.wonDraws;
createdDraws = ESTADISTICAS.createdDraws;

  ngAfterViewInit(): void {
    const ctx = (document.getElementById('barChart') as HTMLCanvasElement).getContext('2d');
    // Configuración del gráfico
    new Chart(ctx!, {
      type: 'bar',
      data: {
        labels: ['Pujas', 'Sorteos'],
        datasets: [{
          label: 'Participadas',
          data: [this.participatedBids, this.participatedDraws],
          backgroundColor: 'rgba(54, 162, 235, 0.6)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }, {
          label: 'Ganadas',
          data: [this.wonBids, this.wonDraws],
          backgroundColor: 'rgba(75, 192, 192, 0.6)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }, {
          label: 'Creadas',
          data: [this.createdBids, this.createdDraws],
          backgroundColor: 'rgba(153, 102, 255, 0.6)',
          borderColor: 'rgba(153, 102, 255, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              precision: 0
            }
          }
        }
      }
    });
  }
}