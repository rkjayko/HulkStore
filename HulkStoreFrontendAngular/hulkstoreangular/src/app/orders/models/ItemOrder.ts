import { Product } from '../../products/models/Product';

export class ItemOrder {
  product: Product;
  quantity: number;
  import: number;

  public calculateAmount(): number {
    return this.quantity * this.product.price;
  }
}
