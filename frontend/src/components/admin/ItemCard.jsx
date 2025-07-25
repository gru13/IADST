import { useState } from "react";
import { IoMailOutline } from "react-icons/io5";
import { MdOutlineLocalPhone } from "react-icons/md";
import { MdOutlineModeEdit } from "react-icons/md";
import { MdOutlineDelete } from "react-icons/md";
import { LiaSave } from "react-icons/lia";
import api from '../../api/axios';




function ItemCard({ element, deleteItem, editValue, setData, setAddToggle, type_, setError }) {
    // console.log(element);

    const [itemData, setItemData] = useState({ ...element })

    const [edit, setEdit] = useState(editValue);

    const handleChanges = (e) => {
        setItemData(
            prev => ({
                ...prev,
                [e.target.name]: e.target.value
            })
        )
    }

    const handleSave = async () => {
        if (edit === false) { return; }

        // Basic validation
        if (type_ === 'teachers') {
            if (!itemData.facultyId?.trim() || !itemData.name?.trim() || !itemData.email?.trim() || !itemData.phoneNo?.trim()) {
                setError("All fields are required");
                return;
            }
        } else { // students
            if (!itemData.rollNumber?.trim() || !itemData.name?.trim() || !itemData.email?.trim() || !itemData.phoneNo?.trim()) {
                setError("All fields are required");
                return;
            }
        }

        // Format validation
        const namePattern = /^[a-zA-Z\s]{2,50}$/;
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const phonePattern = /^(\+91|91|0)?[6-9]\d{9}$/;

        // Type specific validation
        if (type_ === 'teachers') {
            const facultyIdPattern = /^[A-Z]{3}\d{5}$/;
            if (!facultyIdPattern.test(itemData.facultyId)) {
                setError("Faculty ID must be 3 uppercase letters followed by 5 digits (e.g., ABC12345)");
                return;
            }
        } else { // students
            const rollNumberPattern = /^[A-Z]{2}\d{7}$/;
            if (!rollNumberPattern.test(itemData.rollNumber)) {
                setError("Roll number must be 2 uppercase letters followed by 7 digits (e.g., CS2023001)");
                return;
            }
        }

        // Common validation for both types
        if (!namePattern.test(itemData.name)) {
            setError("Name must be 2-50 characters long and contain only letters and spaces");
            return;
        }
        if (!emailPattern.test(itemData.email)) {
            setError("Please provide a valid email address");
            return;
        }
        if (!phonePattern.test(itemData.phoneNo)) {
            setError("Please provide a valid Indian phone number");
            return;
        }

        let url, method = "POST";
        console.log(itemData, type_);

        if (type_ === 'teachers') {
            url = 'http://localhost:8080/admin/teachers';  // Remove trailing slash

            if (itemData.id) {
                url += `/${itemData.id}`;
                method = "PUT"
            }

        } else {
            url = 'http://localhost:8080/admin/students/';

            if (itemData.id) {
                url += itemData.id;
                method = "PUT"
            }

            console.log('students', url, method);
        }


        try {
            const respon = await api(url, {
                method: method,
                headers: {
                    "Content-Type": "application/json",
                },
                data: itemData
            });

            const re = respon.data;

            if (!respon.ok) {
                if (respon.status === 400) {
                    // Handle validation errors from Spring Boot
                    if (re.errors) {
                        const errorMessages = Object.entries(re.errors)
                            .map(([field, msg]) => `${field}: ${msg}`)
                            .join('\n');
                        throw new Error(errorMessages);
                    } else if (re.message) {
                        throw new Error(re.message);
                    }
                }
                throw new Error('An error occurred while saving');
            }

            // Update local state first
            const updatedItem = re;
            if (method === "POST") {
                setData(prev => [updatedItem, ...prev]);
                const newItem = type_ === 'teachers'
                    ? { facultyId: "", name: "", email: "", phoneNo: "" }
                    : { rollNumber: "", name: "", email: "", phoneNo: "" };
                setItemData(newItem);
                setEdit(true); 
                setAddToggle(false); 
            }
            if (setError) setError(null); 
            console.log('all over' + type_);
        } catch (err) {
            console.error('Save error:', err);
            if (setError) setError(err.message);
        }
    };

    const itemStyle = `
        flex group justify-around  font-['fira_code'] border-2 text-xl p-5 pt-10
        border-accent-color-3 w-120 h-70 m-5 rounded-3xl hover:shadow-2xl hover:scale-110 
        hover:shadow-highlight-text-color 
        ${edit ? 'scale-110 ' : 'caret-transparent'}
    `;

    const SideBarStyle = `flex justify-between py-4 text-3xl flex-col group-hover:opacity-100 ${edit ? 'opacity-100' : 'opacity-0'}`

    const InputStyle = `px-2 py-2 grow outline-0 ${edit ? 'underline underline-offset-6' : ''}`

    return (
        <div className={itemStyle}>
            {/* Main Item container */}
            <div className="flex flex-col grow :">
                {/* FacultyId || Rollnumber */}
                <input type="text"
                    className={`text-accent-color-3 ${InputStyle}`}
                    value={type_ === 'teachers' ? itemData.facultyId : itemData.rollNumber}
                    name={type_ === 'teachers' ? "facultyId" : 'rollNumber'}
                    placeholder={type_ === 'teachers' ? "facultyId" : 'RollNumber'}
                    onChange={e => (handleChanges(e))}
                    readOnly={!edit}
                />
                {/* Name */}
                <input type="text"
                    className={`font-bold ${InputStyle}`}
                    value={itemData.name} name="name"
                    onChange={e => (handleChanges(e))}
                    placeholder="Name"
                    readOnly={!edit}
                />
                {/* email */}
                <div className="flex items-center">
                    <IoMailOutline />
                    <input type="text"
                        className={InputStyle}
                        value={itemData.email} name="email"
                        placeholder="Email"
                        onChange={e => (handleChanges(e))}
                        readOnly={!edit}
                    />
                </div>
                {/* phone */}
                <div className="flex items-center">
                    <MdOutlineLocalPhone />
                    <input type="text"
                        className={InputStyle}
                        value={itemData.phoneNo} name="phoneNo"
                        placeholder="Phone Number"
                        onChange={e => (handleChanges(e))}
                        readOnly={!edit}
                    />
                </div>
            </div>
            {/* SideBar */}
            <div className={SideBarStyle}>
                <button className="hover:text-accent-color-3 hover:-rotate-10" onClick={() => { setEdit(!edit); handleSave() }}>
                    {edit ? <LiaSave /> : <MdOutlineModeEdit />}
                </button>
                <button className="hover:text-red-700 hover:scale-125 font-bold" onClick={() => deleteItem(element['id'])}>
                    <MdOutlineDelete />
                </button>
            </div>
        </div>);

}


export default ItemCard;
