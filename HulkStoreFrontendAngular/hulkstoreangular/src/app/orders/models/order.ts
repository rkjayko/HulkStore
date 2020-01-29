import { ItemOrder } from './ItemOrder';

export class Order {
  id: number;
  description: string;
  note: string;
  items: Array<ItemOrder> = [];
  total: number;
  createAt: string;

  calculateTotalAmount(): number {
    this.total = 0;
    this.items.forEach((item: ItemOrder) => {
      this.total += item.calculateAmount();
    });
    return this.total;
  }
}
