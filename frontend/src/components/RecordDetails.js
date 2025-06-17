import { useDispatch } from "react-redux"
import { deleteRecord } from "../redux/recordSlice"

const RecordDetails = ({ record }) => {
  const dispatch = useDispatch()

  const handleClick = async () => {
    const response = await fetch('http://localhost:8080/api/records/' + record.id, {
      method: 'DELETE'
    })

    if (response.ok) {
      const json = await response.json()
      dispatch(deleteRecord(json))
    }
  }

  return (
    <div className="record-details">
      <h4>{new Date(record.date).toLocaleDateString()}</h4>
      <p><strong>{record.title}</strong></p>
      <p>Amount: {record.amount}</p>
      <span className="material-symbols-outlined" onClick={handleClick}>delete</span>
    </div>
  )
}

export default RecordDetails