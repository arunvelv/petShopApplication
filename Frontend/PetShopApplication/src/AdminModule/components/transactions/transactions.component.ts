import { Component } from '@angular/core';
import { Transaction } from '../../../models/Transaction';
import { TransactionsService } from '../../services/transactions/transactions.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-transactions',
  imports: [CommonModule],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css'
})
export class TransactionsComponent {
  transactions: Transaction[] = [];

  constructor(private transactionsService: TransactionsService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees() {
    this.transactionsService.getAllTransactions().subscribe(
      (data: Transaction[]) => {
        this.transactions = data;
      },
      (error) => {
        console.error('Error fetching employees:', error);
      }
    );
  }
}

