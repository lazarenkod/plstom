import java.math.RoundingMode

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 02.03.2009
 * Time: 13:40:56
 */

/*
Постобработчик Комисся за перевод_расчёт
MoneyOrderDBO operation
*/

log.warn('************************** script moneyOrderOperation.feeTransferTotal.afterSum debug start');
def system = operation.payOrderSystem;
def destination = operation.payOrderDestination;
def abbrev = operation.operCurrency;

def tarif_sum = result.value;
def sum_ts1 = scriptUtils.getSum('1405.Сумма_перевода');

def downLimit_WU_NEAR_FAR = BigDecimal.valueOf(3500L);
def step_WU_NEAR_FAR = BigDecimal.valueOf(500L);
def diapasonSum_NEAR_FAR = BigDecimal.valueOf(20L);

def downLimit_WU_CNR = BigDecimal.valueOf(7000L);
def step_WU_CNR = BigDecimal.valueOf(2000L);
def diapasonSum_WU_CNR = BigDecimal.valueOf(20L);

def downLimit_WU_RUR = BigDecimal.valueOf(60000L);
def step_WU_RUR = BigDecimal.valueOf(15000L);
def diapasonSum_WU_RUR = BigDecimal.valueOf(300L);

def downLimit_MIG_RUR = BigDecimal.valueOf(100000L);
def step_MIG_RUR = BigDecimal.valueOf(20000L);
def diapasonSum_MIG_RUR = BigDecimal.valueOf(300L);

def downLimit_MIG_USD_EUR = BigDecimal.valueOf(2000L);
def step_MIG_USD_EUR = BigDecimal.valueOf(500L);
def diapasonSum_MIG_USD_EUR = BigDecimal.valueOf(10L);
def commissionSum = null;

if (!context.isGenerateTransactions()) {
  if (system == 'WU' && (destination == 'FAR' || destination == 'NEAR') && sum_ts1.value.compareTo(downLimit_WU_NEAR_FAR) > 0) {
    if (abbrev == 'USD') {
      extraSum = (sum_ts1.value.subtract(downLimit_WU_NEAR_FAR)).divide(step_WU_NEAR_FAR, 0, java.math.RoundingMode.DOWN).multiply(diapasonSum_NEAR_FAR);
      commissionSum = tarif_sum.add(extraSum);
    }
    if (system == 'WU' && (destination == 'FAR' || destination == 'NEAR') && sum_ts1.value.compareTo(downLimit_WU_NEAR_FAR) <= 0) {
      if (abbrev == 'USD') {
        commissionSum = tarif_sum;
      }
    }
  } else if (system == 'WU' && destination == 'CNR' && sum_ts1.value.compareTo(downLimit_WU_CNR) > 0) {
    if (abbrev == 'USD') {
      extraSum = (sum_ts1.value.subtract(downLimit_WU_CNR)).divide(step_WU_CNR, 0, java.math.RoundingMode.DOWN).multiply(diapasonSum_WU_CNR);
      commissionSum = tarif_sum.add(extraSum);
    }
  } else if (system == 'WU' && destination == 'CNR' && sum_ts1.value.compareTo(downLimit_WU_CNR) <= 0) {
    if (abbrev == 'USD') {
      commissionSum = tarif_sum;
    }
  }
  else if (system == 'WU' && (destination == 'RUSW' || destination == 'BLR') && sum_ts1.value.compareTo(downLimit_WU_RUR) > 0) {
      if (abbrev == 'RUB') {
        extraSum = (sum_ts1.value.subtract(downLimit_WU_RUR)).divide(step_WU_RUR, 0, java.math.RoundingMode.DOWN).multiply(diapasonSum_WU_RUR);
        commissionSum = tarif_sum.add(extraSum);
      }
    }
    else if (system == 'WU' && (destination == 'RUSW' || destination == 'BLR') && sum_ts1.value.compareTo(downLimit_WU_RUR) <= 0) {
        if (abbrev == 'RUB') {
          commissionSum = tarif_sum;
        }
      }
      else if (system == 'MIG' && (destination == 'RUS') && sum_ts1.value.compareTo(downLimit_MIG_RUR) <= 0) {
          if (abbrev == 'RUB') {
            commissionSum = tarif_sum;
          }
        }
        else if (system == 'MIG' && (destination == 'RUS') && sum_ts1.value.compareTo(downLimit_MIG_RUR) > 0) {
            if (abbrev == 'RUB') {
              extraSum = (sum_ts1.value.subtract(downLimit_MIG_RUR)).divide(step_MIG_RUR, 0, java.math.RoundingMode.DOWN).multiply(diapasonSum_MIG_RUR);
              commissionSum = tarif_sum.add(extraSum);
            }
          }
          else if (system == 'MIG' && destination == 'OUT' && sum_ts1.value.compareTo(downLimit_MIG_USD_EUR) > 0) {
              if (abbrev == 'USD' || abbrev == 'EUR')
                extraSum = (sum_ts1.value.subtract(downLimit_MIG_USD_EUR)).divide(step_MIG_USD_EUR, 0, java.math.RoundingMode.DOWN).multiply(diapasonSum_MIG_USD_EUR);
              commissionSum = tarif_sum.add(extraSum);
            }
            else if (system == 'MIG' && destination == 'OUT' && sum_ts1.value.compareTo(downLimit_MIG_USD_EUR) <= 0) {
                if (abbrev == 'USD' || abbrev == 'EUR') {
                  commissionSum = tarif_sum;
                }
              }
              else if (system == 'Coinstar' && destination == 'ALL') {
                  commissionSum = tarif_sum;
                }

  if (commissionSum == null)
    commissionSum = tarif_sum;


  if (tarif_sum == null) {
    throw new IllegalStateException("commision not configured");
  }
  operation.setFeeTransferTotal(commissionSum);
}
log.warn('************************** script moneyOrderOperation.feeTransferTotal.afterSum debug end');

