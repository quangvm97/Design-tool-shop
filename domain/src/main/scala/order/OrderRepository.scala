package order

import com.google.inject.Inject
import common.AbstractUserRepository
import org.joda.time.DateTime


class OrderRepository @Inject()(orderRecord: OrderRecord) extends AbstractUserRepository[Order, OrderRecord]{
  override protected val dao = orderRecord

  override def record2Entity(record: OrderRecord):Order =
    Order(
      id: record.id,
      userId: record.userId,
      createdAt: record.createdAt,
      total: record.total,
      productId: record.productId,
      status: 1
    )
}

