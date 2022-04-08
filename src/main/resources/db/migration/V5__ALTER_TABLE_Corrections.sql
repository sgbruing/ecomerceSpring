ALTER TABLE purchase
    RENAME COLUMN prushace_date TO purchase_date;
ALTER TABLE purchase
    RENAME COLUMN total_purchase TO total_purchased;
ALTER TABLE purchase_product
    ADD quantity_purchased INTEGER NOT NULL;